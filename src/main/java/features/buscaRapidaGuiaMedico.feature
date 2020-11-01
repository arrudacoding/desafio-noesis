#language:pt

@BuscaRapidaGuiaMedico

Funcionalidade: guia medico para a listagem de profissionais separados por área de atuação, nome e localidade.

Esquema do Cenario: Testar a pesquisa na modalidade busca rapida pelo guia medico

Como usuario nao cadastrado
Eu quero utilizar o guia medico para localizar medicos no estado do rj.

Dado que o usuario acesse o portal unimed
E que ele acesse o menu guia medico

Quando for informada uma <Especialidade> valida
E quando for clicado em pesquisar
Então o sistema deve exibir a tela de selecao de estado e cidade

Dado que o usuario selecione <Estado> e <Cidade>
E que tambem seja selecionada a unidade
E que seja clicado em continuar
Então o sistema deve exibir uma lista de profissionais que atuam na regiao e na especialidade selecionadas

Então fecha-se o navegador

Exemplos:
| Especialidade  | Estado  			| Cidade       		|
| "Dermatologia" | "Rio de Janeiro"	| "Nova Iguaçu" 	|