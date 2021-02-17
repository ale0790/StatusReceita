DROP TABLE IF EXISTS `status_receita`.`status`;
DROP TABLE IF EXISTS `status_receita`.`estado`;

CREATE TABLE  `status_receita`.`estado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `status_receita`.`status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data_consulta` datetime NOT NULL,
  `estado_id` int(10) unsigned NOT NULL,
  `status_servico` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_status_estado_id` (`estado_id`),
  CONSTRAINT `FK_status_estado_id` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



