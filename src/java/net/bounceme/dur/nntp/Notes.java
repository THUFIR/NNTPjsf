
package net.bounceme.dur.nntp;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "notes")
@Dependent
public class Notes {

    private String foo = "bar";

    public Notes() {
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
