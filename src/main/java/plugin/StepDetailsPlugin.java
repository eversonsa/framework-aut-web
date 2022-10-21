package plugin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cucumber.api.PickleStepTestStep;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestStepFinished;
import report.Report;

public class StepDetailsPlugin implements ConcurrentEventListener{

    final EventHandler<TestStepFinished> stepHandler = event -> {
        try {
            handleTestStepFinished(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };
    
    public StepDetailsPlugin() {}
    
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, stepHandler);
    }

    
    private void handleTestStepFinished(TestStepFinished event) throws IOException {
        if (event.testStep instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;
            TestCase testCase = event.getTestCase();
            String stepName = getStepName(testStep, testCase);
            
            Report.add(stepName, true);
        }
    }


    private String getStepName(PickleStepTestStep testStep, TestCase testCase) {
        String featureFilePath = testCase.getUri().substring(5);
        int stepLine = testStep.getStepLine() - 1;
        return getStepKeywordFromFeatureFile(featureFilePath, stepLine) + " " + testStep.getStepText();
    }

    //Keyword do step foi capturado dessa forma pois as apis do cucumber retornam apenas o nome sem a keyword do step
    //Um step "Dado que acesso o Google" ficaria "que acesso o Google" por exemplo, o que não ficaria legal em um relatório
    private String getStepKeywordFromFeatureFile(String filename, int line) {
        BufferedReader reader;
        String stepKeyword = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            Object[] lines = reader.lines().toArray();
            stepKeyword = lines[line].toString().trim();
            stepKeyword = stepKeyword.split(" ")[0];
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stepKeyword;
    }

}
