package net.bounceme.dur.nntp;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class MyBean {

    private Long id;

    @PostConstruct
    public void test() {
        System.out.println("post construct called");
    }

    public void actionMethod(ActionEvent event) {
        System.out.println("action called");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
