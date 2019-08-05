package com.isaac.ch12.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 为了序列化和反序列化歌手列表，需要将该类封装到一个容器中——这是XML序列化所必须的，但JSON可以在没有容器类的情况下工作
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singers implements Serializable {
    private List<Singer> singers;
}
