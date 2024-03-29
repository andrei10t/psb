package com.ii.app.config;

import com.ii.app.models.*;
import com.ii.app.models.enums.*;
import com.ii.app.models.user.Address;
import com.ii.app.models.user.User;
import com.ii.app.models.user.UserRole;
import com.ii.app.repositories.*;
import com.ii.app.utils.Constants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class RepositoryInitializer {
    private final BankAccountRepository bankAccountRepository;

    private final CurrencyTypeRepository currencyTypeRepository;

    private final SaldoRepository saldoRepository;

    private final BankAccountTypeRepository bankAccountTypeRepository;

    private final Constants constants;

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final CreditStatusRepository creditStatusRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    private ConversationStatusRepository conversationStatusRepository;

    @Autowired
    private ConversationDirectionRepository conversationDirectionRepository;

    @Autowired
    private InvestmentTypeRepository investmentTypeRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    public RepositoryInitializer(BankAccountRepository bankAccountRepository,
                                 CurrencyTypeRepository currencyTypeRepository,
                                 SaldoRepository saldoRepository,
                                 BankAccountTypeRepository bankAccountTypeRepository,
                                 Constants constants,
                                 UserRoleRepository userRoleRepository,
                                 UserRepository userRepository,
                                 AddressRepository addressRepository,
                                 CreditStatusRepository creditStatusRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bankAccountRepository = bankAccountRepository;
        this.currencyTypeRepository = currencyTypeRepository;
        this.saldoRepository = saldoRepository;
        this.bankAccountTypeRepository = bankAccountTypeRepository;
        this.constants = constants;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.creditStatusRepository = creditStatusRepository;
        this.encoder = bCryptPasswordEncoder;
    }

    @Bean
    public InitializingBean intializeRepo() {
        return () -> {

            if (userRoleRepository.findAll().isEmpty()) {
                userRoleRepository.save(UserRole.builder().userType(UserRole.UserType.ROLE_EMPLOYEE).build());
                userRoleRepository.save(UserRole.builder().userType(UserRole.UserType.ROLE_USER).build());
                userRoleRepository.save(UserRole.builder().userType(UserRole.UserType.ROLE_ADMIN).build());
            }

            if (userRepository.findAll().isEmpty()) {
                Address address = Address.builder()
                    .city("Iasi")
                    .houseNumber("10")
                    .name("Cornel")
                    .surname("Ionescu")
                    .phoneNumber("662003004")
                    .postCode("03-100")
                        .dateOfBirth(Instant.parse("1965-11-30T18:35:24.00Z"))
                    .street("Doamnei nr 54")
                    .build();

                User user = User.builder()
                    .credentials(false)
                    .email("cornel@ionescu.com")
                    .enabled(true)
                    .expired(false)
                    .locked(false)
                    .password(encoder.encode("parola"))
                    .userRoles(Collections.singleton(userRoleRepository.findByUserType(UserRole.UserType.ROLE_ADMIN)))
                    .transactionTemplates(new HashSet<>())
                    .identifier("11111111")
                    .bankAccounts(new HashSet<>())
                    .address(address)
                    .build();

                userRepository.save(user);

                Address address2 = Address.builder()
                    .city("Botosani")
                    .houseNumber("32")
                    .name("Ion")
                    .surname("Anghel")
                    .phoneNumber("521033104")
                    .postCode("60-201")
                        .dateOfBirth(Instant.parse("1955-11-30T18:35:24.00Z"))
                    .street("Ion Agarbiceanu")
                    .build();


                User user2 = User.builder()
                    .credentials(false)
                    .email("ion@anghel.ro")
                    .enabled(true)
                    .expired(false)
                    .locked(false)
                    .password(encoder.encode("parola"))
                    .userRoles(Collections.singleton(userRoleRepository.findByUserType(UserRole.UserType.ROLE_USER)))
                    .transactionTemplates(new HashSet<>())
                    .identifier("22222222")
                    .bankAccounts(new HashSet<>())
                    .address(address2)
                    .build();

                userRepository.save(user2);

                Address address3 = Address.builder()
                    .city("Buzau")
                    .houseNumber("92")
                    .name("Diana")
                    .surname("Ion")
                        .dateOfBirth(Instant.parse("1999-11-30T18:35:24.00Z"))
                    .phoneNumber("692193823")
                    .postCode("50-221")
                    .street("Eleonora Tudor")
                    .build();

                User user3 = User.builder()
                    .credentials(false)
                    .email("diana@ion.ro")
                    .enabled(true)
                    .expired(false)
                    .locked(false)
                    .password(encoder.encode("parola"))
                    .userRoles(Collections.singleton(userRoleRepository.findByUserType(UserRole.UserType.ROLE_EMPLOYEE)))
                    .transactionTemplates(new HashSet<>())
                    .identifier("33333333")
                    .bankAccounts(new HashSet<>())
                    .address(address3)
                    .build();

                userRepository.save(user3);

                Address address4 = Address.builder()
                        .city("Bucuresti")
                        .houseNumber("10")
                        .name("Elena")
                        .surname("Popescu")
                        .phoneNumber("0712345678")
                        .postCode("03-100")
                        .dateOfBirth(Instant.parse("1988-11-30T18:35:24.00Z"))
                        .street("Dorobanti")
                        .build();

                User user4 = User.builder()
                        .credentials(false)
                        .email("elena.pop@gmail.com")
                        .enabled(true)
                        .expired(false)
                        .locked(false)
                        .password(encoder.encode("parola"))
                        .userRoles(Collections.singleton(userRoleRepository.findByUserType(UserRole.UserType.ROLE_USER)))
                        .transactionTemplates(new HashSet<>())
                        .identifier("12345678")
                        .bankAccounts(new HashSet<>())
                        .address(address4)
                        .build();

                userRepository.save(user4);

            }

            if (currencyTypeRepository.findAll().isEmpty()) {
                CurrencyType pln = CurrencyType.builder()
                    .name("RON")
                    .exchangeRate(1f)
                    .build();

                CurrencyType usd = CurrencyType.builder()
                    .name("USD")
                    .exchangeRate(3.88f)
                    .build();

                CurrencyType eur = CurrencyType.builder()
                    .name("EUR")
                    .exchangeRate(4.23f)
                    .build();

                CurrencyType chf = CurrencyType.builder()
                    .name("CHF")
                    .exchangeRate(2.31f)
                    .build();

                CurrencyType gbp = CurrencyType.builder()
                    .name("GBP")
                    .exchangeRate(5.60f)
                    .build();

                currencyTypeRepository.save(pln);
                currencyTypeRepository.save(usd);
                currencyTypeRepository.save(eur);
                currencyTypeRepository.save(chf);
                currencyTypeRepository.save(gbp);
            }

            if (bankAccountTypeRepository.findAll().isEmpty()) {
                BankAccType bankAccType = BankAccType.builder()
                    .bankAccountType(BankAccountType.MULTI_CURRENCY)
                    .exchangeCurrencyCommission((float) constants.CURRENCY_CONVERT_COMMISSION)
                    .transactionComission((float) constants.MULTI_CURRENCY_TRANSFER_COMMISSION)
                    .build();

                bankAccountTypeRepository.save(bankAccType);

                BankAccType bankAccType2 = BankAccType.builder()
                    .bankAccountType(BankAccountType.STANDARD)
                    .exchangeCurrencyCommission((float) constants.CURRENCY_CONVERT_COMMISSION)
                    .transactionComission((float) constants.SINGLE_CURRENCY_TRANSFER_COMMISSION)
                    .build();

                bankAccountTypeRepository.save(bankAccType2);

                BankAccType bankAccType3 = BankAccType.builder()
                    .bankAccountType(BankAccountType.STUDENT)
                    .exchangeCurrencyCommission((float) constants.CURRENCY_CONVERT_COMMISSION)
                    .transactionComission((float) constants.STUDENT_CURRENCY_TRANSFER_COMMISSION)
                    .build();

                bankAccountTypeRepository.save(bankAccType3);
            }

            if (bankAccountRepository.findAll().isEmpty()) {
                BankAccType single = bankAccountTypeRepository.findByBankAccountType(BankAccountType.STANDARD);
                BankAccType multi = bankAccountTypeRepository.findByBankAccountType(BankAccountType.MULTI_CURRENCY);
                BankAccType student = bankAccountTypeRepository.findByBankAccountType(BankAccountType.STUDENT);

                BankAccount bankAccount = BankAccount.builder()
                    .bankAccType(multi)
                    .number("12341234123412341234123412")
                    .removed(false)
                    .saldos(new HashSet<>())
                    .transactions(new HashSet<>())
                    .user(userRepository.findByIdentifier("11111111").get())
                    .build();

                bankAccountRepository.save(bankAccount);

                Set<Saldo> saldos = currencyTypeRepository.findAll()
                    .stream()
                    .map(e -> saldoRepository.save(Saldo.builder()
                        .balance(new BigDecimal(100f))
                        .currencyType(e)
                        .credits(new HashSet<>())
                        .bankAccount(bankAccount)
                        .build()))
                    .collect(Collectors.toSet());


                BankAccount bankAccount2 = BankAccount.builder()
                    .bankAccType(multi)
                    .number("67896789678967896789678967")
                    .removed(false)
                    .saldos(new HashSet<>())
                    .transactions(new HashSet<>())
                    .user(userRepository.findByIdentifier("11111111").get())
                    .build();

                bankAccountRepository.save(bankAccount2);

                Set<Saldo> saldos2 = currencyTypeRepository.findAll()
                    .stream()
                    .map(e -> saldoRepository.save(Saldo.builder()
                        .balance(new BigDecimal(100f))
                        .currencyType(e)
                        .bankAccount(bankAccount2)
                        .credits(new HashSet<>())
                        .build()))
                    .collect(Collectors.toSet());

                BankAccount bankAccount3 = BankAccount.builder()
                    .bankAccType(multi)
                    .number("56785678567856785678567867")
                    .removed(false)
                    .saldos(new HashSet<>())
                    .transactions(new HashSet<>())
                    .user(userRepository.findByIdentifier("12345678").get())
                    .build();

                bankAccountRepository.save(bankAccount3);


                Set<Saldo> saldos3 = currencyTypeRepository.findAll()
                    .stream()
                    .filter(e -> Objects.equals(e.getName(), "RON"))
                    .map(e -> saldoRepository.save(Saldo.builder()
                        .balance(new BigDecimal(100f))
                        .currencyType(e)
                        .credits(new HashSet<>())
                        .bankAccount(bankAccount3)
                        .build()))
                    .collect(Collectors.toSet());


                Set<Saldo> saldos5 = currencyTypeRepository.findAll()
                        .stream()
                        .filter(e -> Objects.equals(e.getName(), "USD"))
                        .map(e -> saldoRepository.save(Saldo.builder()
                                .balance(new BigDecimal(100f))
                                .currencyType(e)
                                .credits(new HashSet<>())
                                .bankAccount(bankAccount3)
                                .build()))
                        .collect(Collectors.toSet());


                Set<Saldo> saldos6 = currencyTypeRepository.findAll()
                        .stream()
                        .filter(e -> Objects.equals(e.getName(), "CHF"))
                        .map(e -> saldoRepository.save(Saldo.builder()
                                .balance(new BigDecimal(100f))
                                .currencyType(e)
                                .credits(new HashSet<>())
                                .bankAccount(bankAccount3)
                                .build()))
                        .collect(Collectors.toSet());


//
//                BankAccount bankAccount10 = BankAccount.builder()
//                        .bankAccType(single)
//                        .number("00785678567856785678567800")
//                        .removed(false)
//                        .saldos(new HashSet<>())
//                        .transactions(new HashSet<>())
//                        .user(userRepository.findByIdentifier("12345678").get())
//                        .build();
//
//                bankAccountRepository.save(bankAccount10);
//
//                Set<Saldo> saldos10 = currencyTypeRepository.findAll()
//                        .stream()
//                        .filter(e -> Objects.equals(e.getName(), "GBP"))
//                        .map(e -> saldoRepository.save(Saldo.builder()
//                                .balance(new BigDecimal(100f))
//                                .currencyType(e)
//                                .credits(new HashSet<>())
//                                .bankAccount(bankAccount10)
//                                .build()))
//                        .collect(Collectors.toSet());
//
//
//                BankAccount bankAccount11 = BankAccount.builder()
//                        .bankAccType(student)
//                        .number("11765678567856785678567800")
//                        .removed(false)
//                        .saldos(new HashSet<>())
//                        .transactions(new HashSet<>())
//                        .user(userRepository.findByIdentifier("12345678").get())
//                        .build();
//
//                bankAccountRepository.save(bankAccount11);
//
//                Set<Saldo> saldos11 = currencyTypeRepository.findAll()
//                        .stream()
//                        .filter(e -> Objects.equals(e.getName(), "RON"))
//                        .map(e -> saldoRepository.save(Saldo.builder()
//                                .balance(new BigDecimal(100f))
//                                .currencyType(e)
//                                .credits(new HashSet<>())
//                                .bankAccount(bankAccount11)
//                                .build()))
//                        .collect(Collectors.toSet());

            }

            if (creditStatusRepository.findAll().isEmpty()) {
                creditStatusRepository.save(CreditStatus.builder().creditType(CreditStatus.CreditType.ACTIVE).build());
                creditStatusRepository.save(CreditStatus.builder().creditType(CreditStatus.CreditType.AWAITING).build());
                creditStatusRepository.save(CreditStatus.builder().creditType(CreditStatus.CreditType.CANCELED).build());
                creditStatusRepository.save(CreditStatus.builder().creditType(CreditStatus.CreditType.PAID).build());
            }

            if (conversationDirectionRepository.findAll().isEmpty()) {
                conversationDirectionRepository.save(ConversationDirection.builder().conversationDirectionType(ConversationDirection.ConversationDirectionType.EMPLOYEE_TO_ADMIN).build());
                conversationDirectionRepository.save(ConversationDirection.builder().conversationDirectionType(ConversationDirection.ConversationDirectionType.USER_TO_EMPLOYEE).build());
            }

            if(conversationStatusRepository.findAll().isEmpty()){
                conversationStatusRepository.save(ConversationStatus.builder().conversationType(ConversationStatus.ConversationType.ACTIVE).build());
                conversationStatusRepository.save(ConversationStatus.builder().conversationType(ConversationStatus.ConversationType.DELETED).build());
                conversationStatusRepository.save(ConversationStatus.builder().conversationType(ConversationStatus.ConversationType.RESOLVED).build());
            }

            if(investmentTypeRepository.findAll().isEmpty()){
                investmentTypeRepository.save(InvestmentType.builder().investmentStatus(InvestmentType.InvestmentStatus.ACTIVE).build());
                investmentTypeRepository.save(InvestmentType.builder().investmentStatus(InvestmentType.InvestmentStatus.CLOSED).build());
            }

            if(investmentRepository.findAll().isEmpty()){
                Instant now = Instant.now();
                investmentRepository.save(Investment.builder().updateTimespan(now).creationDate(now).currency("RON").currentBalance(BigDecimal.valueOf(150L)).startBalance(BigDecimal.valueOf(100L)).investmentType(investmentTypeRepository.findByInvestmentStatus(InvestmentType.InvestmentStatus.ACTIVE)).destinedSaldo(saldoRepository.findAll().get(0)).build());
                investmentRepository.save(Investment.builder().creationDate(now).updateTimespan(now).currency("RON").currentBalance(BigDecimal.valueOf(150L)).startBalance(BigDecimal.valueOf(100L)).investmentType(investmentTypeRepository.findByInvestmentStatus(InvestmentType.InvestmentStatus.CLOSED)).destinedSaldo(saldoRepository.findAll().get(0)).build());
            }
        };
    }
}
