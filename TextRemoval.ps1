# Define the copyright text to remove
$copyrightText = @"
/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
"@

# Get all relevant files
$files = Get-ChildItem -Recurse -Include *.kt, *.java, *.xml, *.gradle, *.kts

foreach ($file in $files) {
    $content = Get-Content -Path $file.FullName -Raw

    if ($content -match "Copyright \(C\) 2023 The Android Open Source Project") {
        Write-Host "Processing: $($file.FullName)"

        $newContent = $content -replace [regex]::Escape($copyrightText), ""
        # Remove leading whitespace/newlines left behind
        $newContent = $newContent.TrimStart()

        Set-Content -Path $file.FullName -Value $newContent -NoNewline
    }
}

Write-Host "Done! Copyright headers removed."