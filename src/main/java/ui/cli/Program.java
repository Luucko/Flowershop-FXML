package ui.cli;

import data.implementation.ExamRepositoryImplementation;
import data.repository.ExamRepository;
import domain.ExamClass;
import util.Crypto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
    private static final Logger LOGGER = Logger.getLogger(Program.class.getName());

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        getVersion();
    }

    private void getVersion() {
        ExamRepositoryImplementation examRepositoryImplementation = new ExamRepositoryImplementation();
        System.out.println(examRepositoryImplementation.repositoryAction());
    }


}
