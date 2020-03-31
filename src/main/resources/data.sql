INSERT INTO  TB_PRODUTO (NOME, VALOR, QUANTIDADE) VALUES('Televisao', 5000, 2);
INSERT INTO  TB_PRODUTO (NOME, VALOR, QUANTIDADE) VALUES('Notebook', 2500, 1);
INSERT INTO  TB_PRODUTO (NOME, VALOR, QUANTIDADE) VALUES('Playstation 4', 2500, 1);
INSERT INTO  TB_PRODUTO (NOME, VALOR, QUANTIDADE) VALUES('Home Theater', 1500, 1);
INSERT INTO  TB_PRODUTO (NOME, VALOR, QUANTIDADE) VALUES('Ar Condicionado', 4500, 1);

INSERT INTO  TB_CLIENTE (CPF, NOME) VALUES('309556410011','AARON FELIPE GRASSMANN');
INSERT INTO  TB_CLIENTE (CPF, NOME) VALUES('861083316026','AARON PAPA DE MORAIS');
INSERT INTO  TB_CLIENTE (CPF, NOME) VALUES('149477850035','ABNER GALLILEI MOREIRA BORGES');

INSERT INTO TB_TRANSACAO (DATA_TRANSACAO, CLIENTE_CPF, VALOR_TOTAL) VALUES (NOW(), '309556410011', 15000);
INSERT INTO TB_TRANSACAO (DATA_TRANSACAO, CLIENTE_CPF, VALOR_TOTAL) VALUES (NOW(), '861083316026', 21000);
INSERT INTO TB_TRANSACAO (DATA_TRANSACAO, CLIENTE_CPF, VALOR_TOTAL) VALUES (NOW(), '149477850035', 10000);

INSERT INTO TB_TRANSACAO_PRODUTO VALUES(1, 1);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(1, 2);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(1, 3);

INSERT INTO TB_TRANSACAO_PRODUTO VALUES(2, 1);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(2, 2);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(2, 3);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(2, 4);
INSERT INTO TB_TRANSACAO_PRODUTO VALUES(2, 5);

INSERT INTO TB_TRANSACAO_PRODUTO VALUES(3, 1);