package pl.edu.utp.model.security;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String functionEnum;
    private String menuName;
    private Boolean active;
    private String menuGroup;

    @ManyToMany(mappedBy = "functions")
    private Collection<Role> roles;

    public Function(){
    }

    public Function(String code, String name, String description, String functionEnum, String menuName, Boolean active, String menuGroup, Collection<Role> roles) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.functionEnum = functionEnum;
        this.menuName = menuName;
        this.active = active;
        this.menuGroup = menuGroup;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionEnum() {
        return functionEnum;
    }

    public void setFunctionEnum(String functionEnum) {
        this.functionEnum = functionEnum;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Function)) return false;

        Function function = (Function) o;

        if (getId() != null ? !getId().equals(function.getId()) : function.getId() != null) return false;
        if (!getCode().equals(function.getCode())) return false;
        if (getDescription() != null ? !getDescription().equals(function.getDescription()) : function.getDescription() != null)
            return false;
        return getRoles() != null ? getRoles().equals(function.getRoles()) : function.getRoles() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getCode().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", functionEnum='" + functionEnum + '\'' +
                ", menuName='" + menuName + '\'' +
                ", active=" + active +
                ", menuGroup='" + menuGroup + '\'' +
                '}';
    }
}