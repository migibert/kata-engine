resource "google_compute_instance" "default" {
  name         = "test"
  machine_type = "f1-micro"
  zone         = "europe-west1-b"

  disk {
    image = "debian-cloud/debian-8"
  }

  network_interface {
    network = "default"
    access_config {
      // Ephemeral IP
    }
  }
}
