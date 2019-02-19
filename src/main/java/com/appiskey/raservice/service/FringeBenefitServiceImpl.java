package com.appiskey.raservice.service;

import com.appiskey.raservice.model.FringeBenefit;
import com.appiskey.raservice.repository.FringeBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by suraksha-app on 2/14/19.
 */

@Service("FringeBenefitService")
public class FringeBenefitServiceImpl implements FringeBenefitService{


    @Autowired
    FringeBenefitRepository fringeBenefitRepository;

    @Override
    public Iterable<FringeBenefit> getAllFringeBenefits() {
        return fringeBenefitRepository.findAllByDeleted(false);
    }

    @Override
    public FringeBenefit createFringeBenefit(FringeBenefit operatingCost) {
        return fringeBenefitRepository.save(operatingCost);

    }

    @Override
    public Optional<FringeBenefit> getFringeBenefitByID(UUID id){

        return fringeBenefitRepository.findById(id);

    }

    @Override
    public Boolean deleteFringeBenefit(UUID id) {
        FringeBenefit fringeBenefit;
        Optional<FringeBenefit> fringeBenefitOptional = fringeBenefitRepository.findById(id);//  findOne(id);
        if (fringeBenefitOptional.isPresent()) {
            fringeBenefit = fringeBenefitOptional.get();
            fringeBenefit.setDeleted(true);
            fringeBenefitRepository.save(fringeBenefit);
            return true;
        } else {

            return false;
        }





    }

    @Override
    public ResponseEntity<FringeBenefit> editFringeBenefit(@RequestBody FringeBenefit fringeBenefit) {
        Optional<FringeBenefit> mFringeBenefit = fringeBenefitRepository.findById(fringeBenefit.getId());
        if (!mFringeBenefit.isPresent())
            return ResponseEntity.notFound().build();
        fringeBenefit.setCreatedAt(mFringeBenefit.get().getCreatedAt());

        return new ResponseEntity<>(fringeBenefitRepository.save(fringeBenefit),HttpStatus.OK);
    }
//    @Override
//    public Iterable<FringeBenefit> searchFringeBenefit(String keyword){
//        Iterable<FringeBenefit> operatingCost =fringeBenefitRepository.findByMonthlyCostContaining(keyword);
//        return operatingCost;
//    }
}