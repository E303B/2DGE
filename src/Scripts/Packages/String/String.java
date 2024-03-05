package Scripts.Packages.String;

import Scripts.Functions.String.FromObject;
import Scripts.Functions.String.ToObject;
import Scripts.Packages.BasePackage;

public class String extends BasePackage{
    public String(){
        super();
        this.childFunctions.add(new FromObject());
        this.childFunctions.add(new ToObject());
    }
}