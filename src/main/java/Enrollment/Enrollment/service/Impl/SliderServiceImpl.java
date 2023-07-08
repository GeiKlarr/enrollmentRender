package Enrollment.Enrollment.service.Impl;

import Enrollment.Enrollment.models.Enrollee;
import Enrollment.Enrollment.models.SliderEntity;
import Enrollment.Enrollment.repository.SliderRepository;
import Enrollment.Enrollment.service.SliderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderServiceImpl implements SliderService {
    private SliderRepository sliderRepository;

    public SliderServiceImpl(SliderRepository sliderRepository) {
        this.sliderRepository = sliderRepository;
    }


    @Override
    public SliderEntity saveSlider(SliderEntity sliderEntity) {
        return sliderRepository.save(sliderEntity);
    }

    @Override
    public List<SliderEntity> findAllPhoto() {
        List<SliderEntity> slider = sliderRepository.findAll();
        return slider.stream().collect(Collectors.toList());
    }

    @Override
    public void updateSlider(SliderEntity sliderEntity) {
        sliderRepository.save(sliderEntity);
    }
}
