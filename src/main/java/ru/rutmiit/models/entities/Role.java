package ru.rutmiit.models.entities;

import jakarta.persistence.*;
import ru.rutmiit.models.enums.UserRoles;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private UserRoles name;

    public Role(UserRoles name) {
        this.name = name;
    }

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false) // добавил nullable для надежности
    public UserRoles getName() {
        return name;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }
}