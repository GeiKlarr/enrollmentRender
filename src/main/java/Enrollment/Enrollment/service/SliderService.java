package Enrollment.Enrollment.service;

import Enrollment.Enrollment.models.Enrollee;
import Enrollment.Enrollment.models.SliderEntity;

import java.util.List;

public interface SliderService {
    SliderEntity saveSlider(SliderEntity sliderEntity);

    List<SliderEntity> findAllPhoto();

    void updateSlider(SliderEntity sliderEntity);
}
