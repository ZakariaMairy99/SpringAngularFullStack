package net.zakaria.springangular;

import net.zakaria.springangular.entities.Payment;
import net.zakaria.springangular.entities.PaymentStatus;
import net.zakaria.springangular.entities.PaymentType;
import net.zakaria.springangular.entities.Student;
import net.zakaria.springangular.repository.PaymentRepository;
import net.zakaria.springangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

	public static void main(String[] args) {
			SpringApplication.run(SpringAngularApplication.class, args);
		}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository) {
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Zakaria").code("112233").programId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Mohamed").code("112234").programId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("lilya").code("000000").programId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Malak").code("112235").programId("SDIA").build());

			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(st -> {
				for (int i = 0; i <10 ; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder().amount(1000+(int)(Math.random()*20000)).
							type(paymentTypes[index]).status(PaymentStatus.CREATED).date(LocalDate.now()).
							student(st).build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
