package com.xiaomizhou.admin.permission;

import com.xiaomizhou.admin.api.Api;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author xiaomizhou
 * @date 2023/10/27
 * @email 521jx123@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_permission")
public class Permission {
    @Id
    private Integer id;

    private String code;

    private String name;

    private Short orderNo;

    @ManyToMany
    @JoinTable(name = "t_permission_api",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "api_id")
    )
    private Set<Api> apis;
}
