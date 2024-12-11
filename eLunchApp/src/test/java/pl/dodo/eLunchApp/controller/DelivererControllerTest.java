package pl.dodo.eLunchApp.controller;

import com.mysql.cj.util.TestUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.repository.DelivererRepository;
import pl.dodo.eLunchApp.service.DelivererService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RequiredArgsConstructor
class DelivererControllerTest {

    private final DelivererService delivererService;
    private final DelivererController delivererController;
    private final DelivererRepository delivererRepository;

    //zprawdzanie usuwania
    private PlatformTransactionManager ptManager;

    private static final String STR_UUID = UUID.randomUUID().toString();

    @Test
    @Transactional
    public void put1() {
        //dodawanie

//
//        DelivererDTOExtended delivererDto = TestUtils.delivererDto(STR_UUID);
//
//        delivererController.put(UUID.fromString(STR_UUID),delivererDto);
//
//        DelivererDTOExtended body = delivererController.get(UUID.fromString(STR_UUID)).getBody();
//
//        assertEquals(body, delivererDto);
    }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus transaction = ptManager.getTransaction(TransactionDefinition.withDefaults());
        //sami ustawiamy transakcje bo nie mozna na raz dodawac i odejmowac
        ptManager.commit(transaction);
    }
}