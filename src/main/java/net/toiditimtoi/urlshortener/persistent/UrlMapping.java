package net.toiditimtoi.urlshortener.persistent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "URL_MAPPING")
public class UrlMapping {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(name = "HASH_URL")
        String hashUrl;

        @Column(name = "FULL_URL")
        String fullUrl;

        public UrlMapping(Long id, String hashUrl, String fullUrl) {
                this.id = id;
                this.hashUrl = hashUrl;
                this.fullUrl = fullUrl;
        }

        public UrlMapping() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getHashUrl() {
                return hashUrl;
        }

        public void setHashUrl(String hashUrl) {
                this.hashUrl = hashUrl;
        }

        public String getFullUrl() {
                return fullUrl;
        }

        public void setFullUrl(String fullUrl) {
                this.fullUrl = fullUrl;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                UrlMapping that = (UrlMapping) o;

                if (!Objects.equals(hashUrl, that.hashUrl)) return false;
                return Objects.equals(fullUrl, that.fullUrl);
        }

        @Override
        public int hashCode() {
                int result = hashUrl != null ? hashUrl.hashCode() : 0;
                result = 31 * result + (fullUrl != null ? fullUrl.hashCode() : 0);
                return result;
        }
}
