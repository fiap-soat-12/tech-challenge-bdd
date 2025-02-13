#language: pt
@TechChallenge

Funcionalidade: Fluxo do pedido

  @TestePositivo
  Cenário: Criar um pedido e evolui-lo até o status FINISHED
    Dado que um cliente cria um pedido e obtem o qrcode
    Quando efetua o pagamento do pedido e o pagamento é confirmado
    Entao o pedido deve ser entregue a cozinha para sua produção e seu status deve ser PREPARING
    E após o preparo, a cozinha deve evoluir o pedido para READY
    E após a retirada do pedido pelo cliente o pedido deve ser evoluido para o status FINISHED

  @TesteNegativo
  Cenário: Criar um pedido e o pagamento é recusado, assim, finalizando o pedido
    Dado que um cliente cria um pedido e obtem o qrcode
    Quando efetua o pagamento do pedido e o pagamento é recusado
    Entao o pedido deve ser evoluido para o status FINISHED