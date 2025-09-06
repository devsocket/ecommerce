variable "cluster_name" {}
variable "location" {}
variable "resource_group_name" {}
variable "node_count" {
  type    = number
  default = 3
}
variable "tags" {
  type    = map(string)
  default = {}
}
variable "ghcr_username" {
  description = "GitHub username for GHCR authentication"
  type        = string
  sensitive   = true
}

variable "ghcr_pat" {
  description = "GitHub Personal Access Token for GHCR"
  type        = string
  sensitive   = true
}