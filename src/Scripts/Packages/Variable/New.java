package Scripts.Packages.Variable;

import Scripts.Functions.VariableFunctions.*;
import Scripts.Packages.BasePackage;

public class New extends BasePackage {
    public New(){
        super();
        childFunctions.add(new Any());
        childFunctions.add(new Constant());
        childFunctions.add(new Typed());
    }
}
