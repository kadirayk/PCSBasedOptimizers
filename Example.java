import java.io.File;
import java.util.Collection;

import ai.libs.hasco.model.Component;
import ai.libs.hasco.serialization.ComponentLoader;
import ai.libs.mlplan.multiclass.wekamlplan.weka.WekaPipelineFactory;
import ai.libs.hasco.pcsbasedoptimization.HASCOToPCSConverter;
import ai.libs.hasco.pcsbasedoptimization.PCSBasedOptimizerInput;
import ai.libs.hasco.pcsbasedoptimization.ComponentInstanceEvaluator;
import ai.libs.hasco.pcsbasedoptimization.HyperBandOptimizer;

public class Example {

	public static void main(String[] args) throws Exception {
		//initialize
		File HASCOFileInput = new File("../mlplan/resources/automl/searchmodels/weka/autoweka.json");
		ComponentLoader cl = new ComponentLoader(HASCOFileInput);
		Collection<Component> components = cl.getComponents();
		String requestedInterface = "BaseClassifier";
		PCSBasedOptimizerInput input = new PCSBasedOptimizerInput(components, requestedInterface);
		WekaPipelineFactory classifierFactory = new WekaPipelineFactory();
		ComponentInstanceEvaluator evaluator = new ComponentInstanceEvaluator(classifierFactory, "testrsc/iris.arff");

		//generate PCS files
		HASCOToPCSConverter.generatePCSFile(input, "PCSBasedOptimizerScripts/HyperBandOptimizer/");
		
		//optimization
		HyperBandOptimizer optimizer = HyperBandOptimizer.HyperBandOptimizerBuilder(input, evaluator)
				.executionPath("PCSBasedOptimizerScripts/HyperBandOptimizer").maxBudget(230.0).minBudget(9.0)
				.nIterations(4).build();
		optimizer.optimize("weka.classifiers.functions.Logistic");

	}

}
