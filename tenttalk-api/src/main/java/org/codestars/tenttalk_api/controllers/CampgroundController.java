package org.codestars.tenttalk_api.controllers;


import org.codestars.tenttalk_api.models.Campground;
import org.codestars.tenttalk_api.models.data.CampgroundRepository;
import org.codestars.tenttalk_api.service.CampgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campground")
@CrossOrigin(origins = "http://localhost:5173")
public class CampgroundController {

    @Autowired
    private CampgroundService campgroundService;
    @Autowired
    private CampgroundRepository campgroundRepository;

    @PostMapping("/add")
    public ResponseEntity<Campground> addCampground(@RequestBody Campground campground) {
        Campground savedCampground = campgroundService.addWithReviews(campground);
        return ResponseEntity.ok(savedCampground);
    }

    @GetMapping("/getAll")
    public List<Campground> getAllCampgrounds(){
        return campgroundRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Campground>> getCampgroundById(@PathVariable Long id) {
        Optional<Campground> campground = campgroundRepository.findById(id);
        if (campground.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(campground);
    }


    @PutMapping("/{id}")
    public Campground updateCampground(@PathVariable Long id, @RequestBody Campground campground) {
        Campground campground1 = campgroundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campground not found"));
        campground1.setName(campground.getName());
        campground1.setAddress(campground.getAddress());
        campground1.setWebsite(campground.getWebsite());
        campground1.setRating(campground.getRating());
        campground1.setReviews(campground.getReviews());
        campground1.setTags(campground.getTags());



        return campgroundRepository.save(campground1);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampground(@PathVariable Long id, @RequestBody Campground campground) {
        Campground campground1 = campgroundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campground not found"));

        campgroundRepository.delete(campground1);
        return ResponseEntity.noContent().build();
    }


}
