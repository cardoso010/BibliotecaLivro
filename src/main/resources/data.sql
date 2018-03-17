insert into Usuario (id, username, email, password) values
	(1,'admin','admin@admin.com.br', 'admin@123'),
	(2,'teste','teste@email.com.br', '44444'),
	(3,'manuel','manuel@email.com.br', '82837');
	
insert into Autor (id, nome) values
	(1,'Joao'),
	(2,'Manoel'),
	(3,'Joaquim');


insert into Cliente (id_cliente, nome, endereco, data_nascimento, observacao) values 
	(1, 'joao', 'Rua teste', '2013-02-04', 'teste'),
	(2, 'Mauricio', 'Rua Mauricio', '1990-01-12', 'Observacao Mauricio'),
	(3, 'Marcos', 'Rua do Marcos', '1992-05-17', 'Observacao Marcos');


insert into Livro (id, nome, quantidade, quantidade_paginas, isbn, autor_id) values
	(1, 'Um bom livro', 10, 200, '3213123', 1),
	(2, 'A vida de programador', 30, 500, '02901', 2),
	(3, 'Programando em spring', 10, 100, '89281', 3);

insert into Review (id, avaliacao, comentario, livro_id, usuario_id) values
	(1, 4, 'Bom', 1, 2),
	(2, 5, 'Otimo', 2, 3),
	(3, 2, 'Muito Otimo', 3, 3),
	(4, 4, 'Demais', 3, 3);
	
insert into Emprestimo (id, data_emprestimo, data_devolucao, cliente_id_cliente, livro_id) values
	(1, '2018-02-01', '2018-02-27', 1, 2),
	(2, '2018-04-01', '2018-04-20', 2, 3),
	(3, '2018-05-01', '2018-05-28', 3, 1);


	
	