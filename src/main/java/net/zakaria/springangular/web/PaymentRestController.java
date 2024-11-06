package net.zakaria.springangular.web;

import net.zakaria.springangular.dtos.NewPaymentDTO;
import net.zakaria.springangular.entities.Payment;
import net.zakaria.springangular.entities.PaymentStatus;
import net.zakaria.springangular.entities.PaymentType;
import net.zakaria.springangular.entities.Student;
import net.zakaria.springangular.repository.PaymentRepository;
import net.zakaria.springangular.repository.StudentRepository;
import net.zakaria.springangular.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository,
                                 PaymentRepository paymentRepository,
                                 PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }
    @GetMapping(path = "/payments")
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }
    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudents(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }
    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
    @GetMapping( "/students/{code}")
    public Student getStudentBycode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }
    @GetMapping("/studentsByProgramId/")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id) {
        return paymentService.updatePaymentStatus(status,id);
    }
    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam("file") MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {
        return paymentService.savePayment(file,newPaymentDTO);
    }
    @GetMapping(path = "paymentField/{paymentId}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }
}
