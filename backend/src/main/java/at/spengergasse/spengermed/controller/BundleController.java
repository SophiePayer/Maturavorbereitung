package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Bundle;
import at.spengergasse.spengermed.model.Bundle;
import at.spengergasse.spengermed.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/bundle")
@CrossOrigin
public class BundleController {

    @Autowired
    private BundleRepository bundleRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Bundle> getAllBundles() {
        return bundleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bundle> getBundle(@PathVariable String id) {
        return bundleRepository
                .findById(id)
                .map(bundle -> ResponseEntity.ok().body(bundle))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Bundle
    @PostMapping()
    public ResponseEntity<Bundle> createBundle(@RequestBody Bundle bundle) {
        bundle.setId(null); //ensures that that Bundle is new
        var saved = bundleRepository.save(bundle);
        return ResponseEntity.created(URI.create("/api/bundle/" + saved.getId())).body(saved);
    }

    //Update Bundle
    @PutMapping("/{id}")
    public ResponseEntity<Bundle> updateBundle(@PathVariable(value = "id") String bundleId, @RequestBody Bundle bundleDetails) {
        return bundleRepository.findById(bundleId).map(bundle -> {
            bundle.setIdentifer((bundleDetails.getIdentifer()));
            bundle.setLinks((bundleDetails.getLinks()));
            bundle.setTotal((bundleDetails.getTotal()));

            Bundle updatedBundle = bundleRepository.save(bundle);
            return ResponseEntity.ok(updatedBundle);
        }).orElseGet(() -> createBundle(bundleDetails));
    }

    //Delete Bundle
    @DeleteMapping("/{id}")
    public ResponseEntity<Bundle> deleteBundle(@PathVariable(value = "id") String bundleId) {
        return bundleRepository
                .findById(bundleId)
                .map(
                        bundle -> {
                            bundleRepository.delete(bundle);
                            return ResponseEntity.ok().<Bundle>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
