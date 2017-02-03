package com.aluguel.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aluguel.models.Despesas;
import com.aluguel.repository.DespesasRepository;

@Component
public class ScheduledTasks {

	@Autowired
	DespesasRepository repository;

	@Autowired
	private MailSender sender;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
		List<Despesas> lista = (List<Despesas>) repository.findByDtVenciomentoBeforeAndIsStatus(Calendar.getInstance(),
				Boolean.FALSE);

		for (Despesas despesas : lista) {
			//enviaEmailCompraProduto(despesas);
		}

		log.info("The time is now {}", dateFormat.format(new Date()));
	}

	private void enviaEmailCompraProduto(Despesas despesas) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Cobrança de Despesas");
		email.setTo(despesas.getInquilino().getEmail());
		email.setText(despesas.getInquilino().getNome() + "Até o momento não identificamos o Pagamento da Conta "
				+ despesas.getConta().getDescricao() + "no Valor" + despesas.getValor());
		System.out.println("Ta Devendo");
		sender.send(email);
	}
}