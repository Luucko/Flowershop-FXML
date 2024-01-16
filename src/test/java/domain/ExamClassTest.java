package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamClassTest {

    @Test
    void getExamId() {
        ExamClass examClass = new ExamClass(2);
        assertEquals(2, examClass.getExamId());
    }
}