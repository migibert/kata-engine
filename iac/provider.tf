provider "google" {
  credentials = "${file("account.json")}"
  project     = "terraform-168420"
  region      = "europe-west1"
}
