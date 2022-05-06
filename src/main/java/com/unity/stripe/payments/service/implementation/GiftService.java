package com.unity.stripe.payments.service.implementation;

import com.unity.stripe.payments.dao.GiftRepository;
import com.unity.stripe.payments.entity.Gift;
import org.springframework.stereotype.Service;

@Service
public class GiftService {


   private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public void saveGift(Gift gift) {
        giftRepository.save(gift);
    }

}
