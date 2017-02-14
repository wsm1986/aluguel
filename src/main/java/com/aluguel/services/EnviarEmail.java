package com.aluguel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.aluguel.models.Despesas;

@Service
public class EnviarEmail {

	@Autowired
	private MailSender sender;

	@Value("${pathServidor}")
	private String path;

	public void enviaEmailCobranca(Despesas despesas) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Cobrança de Despesas");
		email.setTo(despesas.getInquilino().getEmail());
		email.setText(despesas.getInquilino().getNome() + ", Até o momento não identificamos o Pagamento da Conta "
				+ despesas.getConta().getDescricao() + " no Valor: " + despesas.getValor());

		sender.send(email);
	}

	public void enviaComprovante(Despesas despesas) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Comprovante de Pagamento");
		email.setTo(despesas.getInquilino().getEmail());
		email.setText("Segue Link com o comprovante de pagamento: " + path + despesas.getId());

		sender.send(email);
	}

}
