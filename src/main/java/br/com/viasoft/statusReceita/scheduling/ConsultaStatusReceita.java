/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.viasoft.statusReceita.scheduling;

import br.com.viasoft.statusReceita.entity.Estado;
import br.com.viasoft.statusReceita.entity.Status;
import br.com.viasoft.statusReceita.repository.EstadoRepository;
import br.com.viasoft.statusReceita.repository.StatusRepository;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alessandro
 */
@Component
public class ConsultaStatusReceita {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    private final String URL = "http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";
    private final String ONLINE = "imagens/bola_verde_P.png";
    Logger logger = LoggerFactory.getLogger(ConsultaStatusReceita.class);

    //@Scheduled(fixedRate = 300000) // de 5 em 5 min
    @Scheduled(fixedRate = 60000) // de 5 em 5 min
    public void consultaComJsoup() {
        logger.debug(new Date() + " Executou --------------------------------------------------------");
        try {
            Document doc = Jsoup.connect(URL).get();
            Element tabela = doc.getElementsByClass("tabelaListagemDados").first();
            Elements linhas = tabela.getElementsByTag("tr");
            for (int x = 0; x < linhas.size(); x++) {
                if (x != 0) {
                    String est = linhas.get(x).getElementsByTag("td").get(0).ownText();
                    Element situacaoTd = linhas.get(x).getElementsByTag("td").get(5);
                    Element situacaoImg = situacaoTd.getElementsByTag("img").first();
                    String situacao = situacaoImg.attr("src");
                    logger.debug("autorizador: " + est + " situacao: " + situacao);
                    Status status = new Status();
                    status.setDataConsulta(new Date());
                    status.setStatusServico(situacao.equals(ONLINE) ? "ON" : "OFF");
                    Estado estado = estadoRepository.findOneByDescricao(est);
                    if(estado==null){
                        estado = new Estado();
                        estado.setDescricao(est);
                        estadoRepository.save(estado);
                    }
                    status.setEstado(estado);
                    statusRepository.save(status);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}
