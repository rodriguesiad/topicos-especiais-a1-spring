# Sistema de Gerenciamento de Horas Complementares

Este é um sistema desenvolvido para auxiliar no acompanhamento e controle das atividades complementares realizadas por alunos. O objetivo é garantir que os estudantes cumpram a carga horária exigida em cada categoria de atividade, bem como acompanhar a evolução geral do cumprimento das horas.

## 🔧 Funcionalidades

- **Cadastro de Usuário**  
  Crie uma conta para acessar o sistema.

- **Autenticação via Token**  
  Realize login para obter um token JWT e utilizá-lo nos endpoints protegidos.

- **Registro de Atividades**  
  Cadastre atividades realizadas, com carga horária, descrição e categoria.

- **Validação por Subcategoria**  
  O sistema não permite cadastrar atividades cuja carga horária ultrapasse o limite permitido da subcategoria.

- **Situação do Usuário**
    - Porcentagem de Conclusão Geral
    - Porcentagem de Cumprimento por Categoria

- **Visualização de Atividades**  
  Consulte as atividades já cadastradas e seus respectivos detalhes.

## 🔐 Autenticação

Todos os endpoints (exceto cadastro e login) exigem autenticação via token JWT.

Após o login, utilize o token recebido no **header** das requisições, conforme o exemplo:

