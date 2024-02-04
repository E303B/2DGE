package Scripts.Packages;

import Scripts.Functions.VariableFunctions.Set;

public class Variable extends BasePackage {
    public Variable(){
        super();
        this.childPackages.add(new New());
        this.childFunctions.add(new Set());
    }
}
