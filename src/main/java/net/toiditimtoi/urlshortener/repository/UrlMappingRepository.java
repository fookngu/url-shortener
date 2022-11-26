package net.toiditimtoi.urlshortener.repository;

import net.toiditimtoi.urlshortener.persistent.UrlMapping;
import org.springframework.data.repository.CrudRepository;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, Long> {
}
