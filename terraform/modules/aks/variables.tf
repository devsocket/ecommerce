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