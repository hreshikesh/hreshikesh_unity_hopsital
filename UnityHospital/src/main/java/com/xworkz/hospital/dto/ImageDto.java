package com.xworkz.hospital.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private int id;

    private String imageName;

    private String  imageOriginalName;

    private String imagePath;

    private long size;


}
