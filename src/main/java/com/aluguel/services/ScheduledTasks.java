package com.aluguel.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private EnviarEmail sender;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "0 00 8 * * *")
	public void reportCurrentTime() {
		List<Despesas> lista = (List<Despesas>) repository.findByDtVenciomentoBeforeAndIsStatus(Calendar.getInstance(),
				Boolean.FALSE);

		for (Despesas despesas : lista) {
			sender.enviaEmailCobranca(despesas);
		}

		log.info("The time is now {}", dateFormat.format(new Date()));
	}

	
}