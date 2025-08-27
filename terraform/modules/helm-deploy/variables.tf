variable "release_name" {}
variable "helm_chart_path" {}
variable "namespace" {}
variable "values_file" {}
variable "kube_config" { type = map(string)}
variable "cluster_name" {}