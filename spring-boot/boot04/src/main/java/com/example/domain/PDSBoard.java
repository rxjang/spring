package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name = "tbl_pds")
@EqualsAndHashCode(of = "pid")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PDSBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pname;

    private String pwriter;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdsno")
    private List<PDSFile> files;
}
