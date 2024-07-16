package com.example.demo;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.111:3000"})
@RestController
public class AdController {

    private final List<AdInfo> mockAds;
    private final Map<AdType, Float> priceList;

    public AdController() {
        Random r = new Random(1);
        priceList = new HashMap<>() {{
            put(AdType.FACEBOOK, 135.5f);
            put(AdType.INSTAGRAM, 124.5f);
            put(AdType.X, 103.1f);
            put(AdType.GOOGLE, 198.5f);
        }};
        mockAds = new ArrayList<>() {{
            for (int i = 0; i < 1000; i++) {
                add(generate(r));
            }
        }};
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ads")
    List<AdInfo> all() {
        return mockAds;
    }

    @PostMapping("/ads")
    boolean newInfo(@RequestBody AdInfo newInfo) {
        return mockAds.add(newInfo);
    }

    @GetMapping("/ads/{index}")
    AdInfo byId(@PathVariable int index) {
        if (mockAds.size() <= index)
            throw new AdNotFoundException((index));
        return mockAds.get(index);
    }

    @PutMapping("/ads/{index}")
    AdInfo edit(@RequestBody AdInfo info, @PathVariable int index) {
        if (mockAds.size() <= index)
            throw new AdNotFoundException((index));
        AdInfo ad = mockAds.get(index);
        ad.setType(info.getType());
        ad.setShownDate(info.getShownDate());
        ad.setIsClicked(info.getIsClicked());
        return ad;
    }

    @DeleteMapping("/ads/{index}")
    void delete(@PathVariable int index) {
        if (mockAds.size() <= index)
            throw new AdNotFoundException((index));
        mockAds.remove(index);
    }

    @GetMapping("/price")
    Map<AdType, Float> getPriceList() {
        return priceList;
    }


    private AdInfo generate(Random r) {
        AdType type = AdType.values()[r.nextInt(AdType.values().length)];
        Date shownDate = new Date(r.nextLong(new Date().getTime() - 10368000000L, new Date().getTime()));
        boolean isClicked = r.nextBoolean();
        return new AdInfo(type, shownDate, isClicked);
    }
}
