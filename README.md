# StatusReceita

Bando de dados utilizado: MariaBD 10.4

Link para download da base: https://github.com/ale0790/StatusReceita/blob/main/src/main/resources/status_receita%2020210217%200848.zip

Aplicação utilizando Spring Boot e OpenJDK 14.

1- Utilizando a biblioteca https://jsoup.org/, você deve ser capaz de ler e armazenar em um banco de dados de sua escolha os status dos serviços de nota fiscal eletrônica por estado, acessando a página http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx.

OK

2- Desenvolva um job que seja executado a cada 5 minutos para armazenar um histórico de status dos serviços.

OK

3- Retornar por rest os status atual dos serviços por estado.

http://localhost:8080/estados/status

4- Retornar por rest o status atual do serviço filtrando por estado.

http://localhost:8080/estados/status?estado=AM

5- Retornar por rest os status dos serviços por estado filtrando por data.

http://localhost:8080/estados/data?dataConsulta=11-02-2021

6- Retornar por rest qual estado teve mais indisponibilidade de serviço.

http://localhost:8080/estados/indisponibilidade
