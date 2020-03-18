# dao-jdbc

Este projeto demonstra como é feita a conexão com banco de dados usando a tecnologia JDBC, que é a API padrão do java para acesso a dados.

## Começando
Tecnologias e ferramenta para rodar o projeto:

* [Eclipse: Para desenvolvimento do projeto](https://www.eclipse.org/downloads/packages/)
* [Banco de dados MySQL](https://dev.mysql.com/downloads/windows/installer/8.0.html)
* [Conector MySQL](https://dev.mysql.com/downloads/connector/j/)

Dados de conexão com o banco:

| Hostname | Port | DB | User | Password |
|:--:|:--:|:--|:--:|:--|
| localhost | 3306 | daojdbc | desenvolvedor  | 1234 |


## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

>cd "diretorio de sua preferencia"
>
>git clone https://github.com/heberRibeiro/find-devs


## Configurações
Para executar o projeto, é necessário iniciar o banco de dados MySQL. Caso ele tenha sido configurado como serviço, verificar o status de execução, caso contrário, seguir os seguintes passos:
###### Iniciando o MySQL (caso não tenha sido configurado como serviço)
Abrir o Power Shel ou Prompt de Comando (usuários windows) e entre na pasta bin do mysql:

> cd "C:/Program Files/MySQLMySQL Server 5.7/bin"

Em seguida execute o comando:

>mysqld --console

Para parar o serviço, basta executar o esse comando em outra janela do prompt:

>mysqladmin -h localhost -u root shutdown

[Seguir este link, em caso de dúvida](https://dev.mysql.com/doc/refman/8.0/en/windows-start-command-line.html)

Será necessário adicionar o drive de conexão MySQL ao projeto. Para fazer isso, seguir os seguintes passo no Eclipse IDE:

>Clicar em Windows na barra em seguida em "Preferences".
Na janela Preferences, clicar em "Java", em seguida em "Build Path" e "User Libraries". Criar uma nova biblioteca de usuário clicando e "New", como sugestão,
usar o nome "MySQLConnector" para a nova biblioteca criada.
Após isso, selecionar a biblioteca criada e clicar no botão "Add External JARs..." e selecionar o conector baixado do link indicado acima.


O acesso e gerenciamento pode se dar através do MySQL WorkBench com os dados informados acima.
