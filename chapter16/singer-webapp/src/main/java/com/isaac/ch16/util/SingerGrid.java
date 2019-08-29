package com.isaac.ch16.util;

import com.isaac.ch16.entities.Singer;
import lombok.Data;

import java.util.List;

@Data
public class SingerGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Singer> singerData;

}
