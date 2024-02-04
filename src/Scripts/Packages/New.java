package Scripts.Packages;

import Scripts.Functions.VariableFunctions.*;

public class New extends BasePackage {
    public New(){
        super();
        childFunctions.add(new Any());
        childFunctions.add(new Constant());
        childFunctions.add(new Typed());
    }
}
