package edu.alenkin.topjavagraduation.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public abstract class AbstractServiceTest {
}
