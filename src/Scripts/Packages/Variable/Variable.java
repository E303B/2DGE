package Scripts.Packages.Variable;

import Scripts.Functions.VariableFunctions.*;
import Scripts.Packages.BasePackage;

public class Variable extends BasePackage {
    public Variable(){
        super();
        this.childPackages.add(new New());
        this.childFunctions.add(new Set());
        this.childFunctions.add(new Get());
        this.childFunctions.add(new Del());
    }
}
