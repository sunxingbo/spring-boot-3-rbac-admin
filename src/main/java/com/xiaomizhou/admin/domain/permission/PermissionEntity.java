package com.xiaomizhou.admin.domain.permission;

import com.xiaomizhou.admin.domain.api.ApiEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "菜单编号不允许为空")
    private String code;

    @NotEmpty(message = "菜单名称不允许为空")
    private String name;

    private Short orderNo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_permission_api",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "api_id")
    )
    private Set<ApiEntity> apis;
}
