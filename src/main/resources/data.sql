insert into categoria(id_categoria, nome, data_criacao, data_alteracao) values
(1, 'Atividades de Ensino e Iniciação à Docência', NOW(), null),
(2, 'Atividades de Pesquisa', NOW(), null),
(3, 'Atividades de extensão e atividades/eventos variados', NOW(), null);

insert into subcategoria(nome, id_categoria, carga_horaria_maxima, data_criacao, data_alteracao) values
    ('Monitoria reconhecida pela UNITINS e/ou voluntária', 1, 40, NOW(), null),
    ('Disciplinas de outros cursos', 1, 60, NOW(), null),
    ('Projetos e oficinas temáticas', 1, 30, NOW(), null),
    ('Experiência Profissional', 1, 30, NOW(), null);

insert into subcategoria(nome, id_categoria, carga_horaria_maxima, data_criacao, data_alteracao) values
('Iniciação científica ', 2, 60, NOW(), null),
('Trabalho desenvolvido em eventos científicos ou seminários com publicações detrabalhos periódicos especializados, anais de congressos e similares', 2, 45, NOW(), null),
('Participação em grupos de pesquisa', 2, 60, NOW(), null);

insert into subcategoria(nome, id_categoria, carga_horaria_maxima, data_criacao, data_alteracao) values
('Participação em seminários, congressos, encontros estudantis', 3, 60, NOW(), null),
('Participação em cursos de extensão e atualização, na área do curso ou afins', 3, 40, NOW(), null),
('Participação em visitas técnicas programadas e ou viagens científicas e de estudo', 3, 20, NOW(), null),
('Participação na organização, coordenação ou realização de cursos e/ou eventos científicos internos ou externos à UNITINS, na área do curso ou afins', 3, 20, NOW(), null),
('Participação em intercâmbios institucionais ou culturais', 3, 20, NOW(), null),
('Trabalho na organização ou participação em campanhas de voluntariado ou programas de ação social', 3, 20, NOW(), null),
('Participação ou trabalho na organização de jornal informativo da instituição, home page do curso, incubadora, agência ou escritório experimental/modelo', 3, 20, NOW(), null),
('Estágios extracurricularess', 3, 40, NOW(), null),
('Cursos de idiomas, comunicação e expressão e de informática', 3, 30, NOW(), null),
('Participação ou trabalho na organização em campanhas da UNITINS', 3, 15, NOW(), null),
('Participação como membro do Colegiado do Curso, CONSEPE e/ou CONSUNI ', 3, 30, NOW(), null);


