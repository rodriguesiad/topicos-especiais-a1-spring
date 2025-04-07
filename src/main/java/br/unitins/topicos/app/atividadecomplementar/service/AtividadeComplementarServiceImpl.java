package br.unitins.topicos.app.atividadecomplementar.service;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarRequest;
import br.unitins.topicos.app.atividadecomplementar.repository.AtividadeComplementarRepository;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.subcategoria.service.SubcategoriaService;
import br.unitins.topicos.app.usuario.service.UsuarioService;
import br.unitins.topicos.app.usuariosituacao.model.UsuarioSituacaoResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AtividadeComplementarServiceImpl extends BaseServiceIpml<AtividadeComplementar> implements AtividadeComplementarService {

    private final SubcategoriaService subcategoriaService;
    private final UsuarioService usuarioService;
    private final AtividadeComplementarRepository atividadeComplementarRepository;

    protected AtividadeComplementarServiceImpl(final BaseRepository<AtividadeComplementar> repository,
                                               final SubcategoriaService subcategoriaService,
                                               final UsuarioService usuarioService,
                                               final AtividadeComplementarRepository atividadeComplementarRepository) {
        super(repository);
        this.subcategoriaService = subcategoriaService;
        this.usuarioService = usuarioService;
        this.atividadeComplementarRepository = atividadeComplementarRepository;
    }

    @Override
    @Transactional
    public AtividadeComplementar create(AtividadeComplementar entity) throws ApiException {
        Integer cargaHorariaMaxima = subcategoriaService.findById(entity.getSubcategoria().getId()).getCargaHorariaMaxima();

        Integer cargaHorariaAtual = atividadeComplementarRepository
                .calcularCargaHorariaPorSubcategoria(entity.getSubcategoria().getId());

        if (cargaHorariaAtual + entity.getCargaHoraria() > cargaHorariaMaxima) {
            throw new ApiException("Carga horária excedida para a subcategoria");
        }

        return super.create(entity);
    }

    @Transactional
    public AtividadeComplementar update(Integer id, AtividadeComplementarRequest request) throws ApiException {
        AtividadeComplementar entity = this.findById(id);

        if (Objects.nonNull(request.getTitulo()) && !request.getTitulo().isBlank()) {
            entity.setTitulo(request.getTitulo());
        }

        if (Objects.nonNull(request.getDescricao()) && !request.getDescricao().isBlank()) {
            entity.setDescricao(request.getDescricao());
        }

        if (request.getCargaHoraria() != null) {
            Integer cargaHorariaMaxima = entity.getSubcategoria().getCargaHorariaMaxima();
            Integer cargaHorariaAtual = atividadeComplementarRepository.calcularCargaHorariaPorSubcategoria(entity.getSubcategoria().getId()) - entity.getCargaHoraria();

            if (cargaHorariaAtual + request.getCargaHoraria() > cargaHorariaMaxima) {
                throw new ApiException("Carga horária excedida para a subcategoria");
            }

            entity.setCargaHoraria(request.getCargaHoraria());
        }

        if (request.getSubcategoria() != null) {
            entity.setSubcategoria(subcategoriaService.findById(request.getSubcategoria()));
        }

        if (request.getUsuario() != null) {
            entity.setUsuario(usuarioService.findById(request.getUsuario()));
        }

        return getRepository().save(entity);
    }

    @Transactional
    public void delete(Integer id) throws ApiException {
        AtividadeComplementar entity = this.findById(id);
        getRepository().delete(entity);
    }

    @Override
    public AtividadeComplementarRepository getRepository() {
        return (AtividadeComplementarRepository) super.getRepository();
    }

    @Transactional
    public UsuarioSituacaoResponse calcularSituacaoDoUsuario(Integer usuarioId) throws ApiException {
        List<AtividadeComplementar> atividadesUsuario = getRepository().findByUsuarioId(usuarioId);

        if (atividadesUsuario.isEmpty()) {
            throw new ApiException("O usuário não possui atividades registradas.");
        }

        Map<String, Integer> cargaHorariaPorCategoria = new HashMap<>();
        int cargaHorariaTotal = 0;

        for (AtividadeComplementar atividade : atividadesUsuario) {
            String nomeCategoria = atividade.getSubcategoria().getCategoria().getNome();

            cargaHorariaPorCategoria.merge(
                    nomeCategoria,
                    atividade.getCargaHoraria(),
                    Integer::sum
            );

            cargaHorariaTotal += atividade.getCargaHoraria();
        }

        double porcentagemGeral = Math.min(Math.round((cargaHorariaTotal * 100.0 / 140.0) * 100.0) / 100.0, 100.0);

        List<UsuarioSituacaoResponse.CategoriaProgresso> progressoPorCategoria = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cargaHorariaPorCategoria.entrySet()) {
            String nomeCategoria = entry.getKey();
            Integer horasNaCategoria = entry.getValue();

            Double porcentagemCategoria = Math.round((horasNaCategoria * 100.0 / 140.0) * 100.0) / 100.0;

            progressoPorCategoria.add(
                    UsuarioSituacaoResponse.CategoriaProgresso.builder()
                            .nomeCategoria(nomeCategoria)
                            .percentualConclusao(porcentagemCategoria)
                            .build()
            );
        }

        return UsuarioSituacaoResponse.builder()
                .percentualConclusaoGeral(porcentagemGeral)
                .progressoPorCategoria(progressoPorCategoria)
                .build();
    }

    @Override
    @Transactional
    public List<AtividadeComplementar> buscarPorUsuarioId(Integer usuarioId) throws ApiException {
        List<AtividadeComplementar> atividades = getRepository().findByUsuarioId(usuarioId);
        if (atividades.isEmpty()) {
            throw new ApiException("Nenhuma atividade encontrada para o usuário com ID: " + usuarioId);
        }
        return atividades;
    }
}
