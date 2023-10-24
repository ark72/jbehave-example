package net.masterthought.example;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.PrintStreamStepMonitor;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

public class ATMScenario extends JUnitStory {

    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                // Fail upon Pending
                //.usePendingStepStrategy(new FailingUponPendingStep())
                // where to find the stories
                .useStepMonitor(new PrintStreamStepMonitor())
                //.doDryRun(true)
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                // CONSOLE and TXT reporting
                //.useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(StoryReporterBuilder.Format.CONSOLE, StoryReporterBuilder.Format.TXT, StoryReporterBuilder.Format.HTML, StoryReporterBuilder.Format.XML));
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withDefaultFormats());
    }

    // Here we specify the steps classes
    //@Override
    //public List<CandidateSteps> candidateSteps() {
    @Override
    public InjectableStepsFactory stepsFactory() {
        // varargs, can have more that one steps classes
        //return new InstanceStepsFactory(configuration(), new ATMScenarioSteps()).createCandidateSteps();
        return new InstanceStepsFactory(configuration(), new ATMScenarioSteps());
    }
}