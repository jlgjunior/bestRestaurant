# bestRestaurant
System to vote and choose a restaurant to lunch during the week
<br/>
O projeto foi feito em ambiente linux com a IDE eclipse e roda no Maven. É uma aplicação de console. Inicialmente seria uma aplicação com Spring MVC mas devido ao tempo gasto com vários problemas com configurar o ambiente para ler arquivos JSP no lugar do HTML (alguns problemas podem ser vistos no histórico de commits) o projeto foi mudado para uma aplicação de terminal.
<br/>
<br/>
Ao rodar o comando sudo mvn clean install na pasta principal do projeto, os testes unitários serão executados, e ao passarem, o .jar é gerado. Eu adicionei o jar excepcionalente nesse projeto pois em caso de problemas de configuração do ambiente, é possível executar a aplicação.
<br/>
<br/>
Ao terminar o build utilizando o comando mvn citado acima, o arquivo jar deve estar localizado na pasta target localizada na pasta do projeto, então, estando na raíz do mesmo, para executar o .jar se deve executar no terminal o comando "java -jar target/bestRestaurants-0.0.1-SNAPSHOT-jar-with-dependencies.jar".<br/>
<br/>
<br/>
O sistema não permite que um restaurante seja escolhido durante a mesma semana, e também não permite que um usuário vote em mais de um restaurante no mesmo dia (nem mais de uma vez no mesmo).
<br/>
O sistema não permite votação após o meio-dia.
<br/>
O sistema tem as opções 2, 3 e 4 para fins de testes.
<br/>
A opção 5 exibe o restaurante que está ganhando até o momento atual (e que após ao meio-dia é defitivamente o vencedor do dia).
<br/>
<br/>
<br/>
Acabei utilizando métodos estáticos para simular recuperações de base dados.
