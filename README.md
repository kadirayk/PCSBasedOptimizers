# Installation
- install SMAC:
  - execute `apt-get install swig`
  - execute `pip install smac`
- install HyperBandSter:
  - execute `pip install hpbandster`
- clone this repository and place "PSCBasedOptimizerScripts" folder inside "softwareconfiguration/hasco" folder
- run this test class as junit test: "ai.libs.hasco.pcsbasedoptimizer.PCSBasedOptimizerTest"


# Using Optimizers
### Initializing
- Initialize a ComponentLoader with a HASCO component description file (.json) and get collection of components.
- Define requestedInterface and instantiate a PCSBasedOptimizerInput
- Instantiate a ComponentInstanceEvaluator with WekaPipelineFactory and an arff file
```java
ComponentLoader cl = new ComponentLoader(HASCOFileInput);
Collection<Component> components = cl.getComponents();
String requestedInterface = "BaseClassifier";
PCSBasedOptimizerInput input = new PCSBasedOptimizerInput(components, requestedInterface);
WekaPipelineFactory classifierFactory = new WekaPipelineFactory();
IObjectEvaluator<ComponentInstance, Double> evaluator = new ComponentInstanceEvaluator(classifierFactory, "testrsc/iris.arff");
```
### Generating PCS Files
- Pass the input you instantiated above and give a destination folder for the generated PCS files
```java
HASCOToPCSConverter.generatePCSFile(input, "PCSBasedOptimizerScripts/BOHBOptimizer/");
```

### Executing Optimize
- Pass the input and the evaluator you instantiated above to an optimizerBuilder e.g. `BOHBOptimizer.BOHBOptimizerBuilder` (Other available Optimizers are `SMACOptimizer` and `HyperBandOptimizer`)
- Define the executionPath it should be same folder that the PCS files are generated
- Define optimizer options e.g. maxBudget, minBudget etc.
- Execute optimize() with a component name
```java
BOHBOptimizer optimizer = BOHBOptimizer.BOHBOptimizerBuilder(input, evaluator)
				.executionPath("PCSBasedOptimizerScripts/BOHBOptimizer").maxBudget(230.0).minBudget(9.0).nIterations(4)
				.build();
optimizer.optimize("weka.classifiers.functions.Logistic");
``` 
