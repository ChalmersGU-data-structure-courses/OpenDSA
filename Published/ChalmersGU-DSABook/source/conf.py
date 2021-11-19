# -*- coding: utf-8 -*-
#
# OpenDSA documentation build configuration file, created by
# sphinx-quickstart on Sat Mar 17 18:07:39 2012.
#
# This file is execfile()d with the current directory set to its containing dir.
#
# Note that not all possible configuration values are present in this
# autogenerated file.
#
# All configuration values have a default; values that are commented out
# serve to show the default.

import sys, os
import json

#checking if we are building a book or class notes (slides)
on_slides = os.environ.get('SLIDES', None) == "yes"
# If extensions (or modules to document with autodoc) are in another directory,
# add these directories to sys.path here. If the directory is relative to the
# documentation root, use os.path.abspath to make it absolute, like shown here.
#sys.path.insert(0, os.path.abspath('.'))

# -- General configuration -----------------------------------------------------

# If your documentation needs a minimal Sphinx version, state it here.
# needs_sphinx = '2.4'

# Add any Sphinx extension module names here, as strings. They can be extensions
# coming with Sphinx (named 'sphinx.ext.*') or your custom ones.

extensions = ['sphinx.ext.autodoc', 'sphinx.ext.doctest', 'sphinx.ext.todo', 'sphinx.ext.mathjax', 'sphinx.ext.ifconfig']

ourCustoms = ['avembed', 'avmetadata', 'extrtoolembed', 'codeinclude', 'numref', 'chapnum', 'odsalink', 'odsascript', 'inlineav', 'html5', 'odsafig', 'odsatable', 'chapref', 'odsatoctree', 'showhidecontent', 'iframe']

customsDir = '/home/noname/data-structures/OpenDSA/RST/ODSAextensions/odsa/'
for c in ourCustoms:
	sys.path.append(os.path.abspath(customsDir + c))
	extensions.append(c)

# One custom extension that breaks the naming convention:
sys.path.append(os.path.abspath(customsDir + 'sphinx-numfig'))
extensions.append('numfig')

slides_lib = ''

#only import hieroglyph when building course notes
if slides_lib == 'hieroglyph':
  extensions.append('hieroglyph')

# Add any paths that contain templates here, relative to this directory.
templates_path = ['_templates']

# The suffix of source filenames.
source_suffix = '.rst'

# The encoding of source files.
#source_encoding = 'utf-8-sig'

# The master toctree document.
master_doc = 'index'

# General information about the project.
project = u'OpenDSA'
copyright = u'2016 by OpenDSA Project Contributors and distributed under an MIT license'

# The version info for the project you're documenting, acts as replacement for
# |version| and |release|, also used in various other places throughout the
# built documents.
#
# The short X.Y version.
version = '0'
# The full version, including alpha/beta/rc tags.
release = '0.4.1'

# The language for content autogenerated by Sphinx. Refer to documentation
# for a list of supported languages.
language = "en"
locale_dirs = ['locale/']   # path is example but recommended.
gettext_compact = False     # optional

# There are two options for replacing |today|: either, you set today to some
# non-false value, then it is used:
#today = ''
# Else, today_fmt is used as the format for a strftime call.
#today_fmt = '%B %d, %Y'

# List of patterns, relative to source directory, that match files and
# directories to ignore when looking for source files.
exclude_patterns = []

# The reST default role (used for this markup: `text`) to use for all documents.
#default_role = None

# If true, '()' will be appended to :func: etc. cross-reference text.
#add_function_parentheses = True

# If true, the current module name will be prepended to all description
# unit titles (such as .. function::).
#add_module_names = True

# If true, sectionauthor and moduleauthor directives will be shown in the
# output. They are ignored by default.
#show_authors = False

#language to highlight source code in
highlight_language = 'guess' #'{"Pseudo": {"ext": ["txt"], "label": "Pseudo-code", "lang": "java"}, "Java_Generic": {"ext": ["java"], "label": "Java", "lang": "java"}, "Python": {"ext": ["py"], "label": "Python", "lang": "python"}}'

# The name of the Pygments (syntax highlighting) style to use.
pygments_style = 'sphinx' #'sphinx'

# A list of ignored prefixes for module index sorting.
#modindex_common_prefix = []

# -- Options for HTML Slide output ---------------------------------------------------
sys.path.append('/home/noname/data-structures/OpenDSA/RST/_themes')
slide_theme_path = ['/home/noname/data-structures/OpenDSA/RST/_themes']

# Themes that are defined by hieroglyph; using them breaks some custom ODSA content
# slide_theme = 'single-level' # basic theme
# slide_theme = 'slides' # default theme
# slide_theme = 'slides2' # animated theme

# Custom themes, made for ODSA content:
slide_theme = 'slidess' # working, default theme for all slides
# The 'haiku' theme is not for slides

#slide_theme_options = {'custom_css':'custom.css'}

slide_link_html_to_slides = not on_slides
slide_link_html_sections_to_slides = not on_slides
#slide_relative_path = "./slides/"

slide_link_to_html = True
slide_html_relative_path = "../"


# -- Options for HTML output ---------------------------------------------------
#The fully-qualified name of a HTML Translator, that is used to translate document
#trees to HTML.
html_translator_class = 'html5.HTMLTranslator'


# The theme to use for HTML and HTML Help pages.  See the documentation for
# a list of builtin themes.
sys.path.append('/home/noname/data-structures/OpenDSA/RST/_themes')
html_theme_path = ['/home/noname/data-structures/OpenDSA/RST/_themes']
if on_slides:
   html_theme = 'slides'
else:
   html_theme = 'haiku'

# Theme options are theme-specific and customize the look and feel of a theme
# further.  For a list of options available for each theme, see the
# documentation.
html_theme_options = json.loads('{}')

# Add any paths that contain custom themes here, relative to this directory.
#html_theme_path = []

# The name for this set of Sphinx documents.  If None, it defaults to
# "<project> v<release> documentation".
html_title = 'Data Structures and Algorithms'

# A shorter title for the navigation bar.  Default is the same as html_title.
#html_short_title = None

# The name of an image file (relative to this directory) to place at the top
# of the sidebar.
html_logo =  "_static/OpenDSALogoT64.png"

# The name of an image file (within the static path) to use as favicon of the
# docs.  This file should be a Windows icon file (.ico) being 16x16 or 32x32
# pixels large.
#html_favicon = None

# Add any paths that contain custom static files (such as style sheets) here,
# relative to this directory. They are copied after the builtin static files,
# so a file named "default.css" will overwrite the builtin "default.css".
html_static_path = ['_static']

# Manipulates the lists of scripts that jQuery automatically loads
# The Sphinx-generated search page is dependent on certain files being loaded in the head element whereas
# we normally want to load these scripts in the body to make the page load faster
# 'script_files' will be loaded in the head element on search.html and in the body on all other pages
# (setting this value here overrides Sphinx's default script files, so we have to add 'underscore.js' and 'doctools.js')
# 'search_scripts' are only loaded on the search page
# 'odsa_scripts' will be loaded as part of the body on all pages
# 'css_files' adds our custom CSS files to be loaded in the head element so that page doesn't have to re-render
# all the content that loaded before the CSS files
# 'odsa_root_path' specifies the relative path from the HTML output directory to the ODSA root directory and is used
# to properly link to Privacy.html
# The code that appends these scripts can be found in RST/_themes/haiku/layout.html and basic/layout.html

html_context = {"script_files": [
                  #'https://code.jquery.com/jquery-2.1.4.min.js',
                  '../../../lib/jquery.min.js',
                  '../../../lib/jquery.migrate.min.js',
                  '//cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS-MML_HTMLorMML',
                  '//cdnjs.cloudflare.com/ajax/libs/localforage/1.9.0/localforage.min.js',
                  '../../../lib/accessibility.js' 
                ],
                "search_scripts": [
                  '_static/underscore.js',
                  '_static/doctools.js'
                ],
                "odsa_scripts": [
                  #'https://code.jquery.com/ui/1.11.4/jquery-ui.min.js',
                  '../../../lib/jquery.ui.min.js',
                  '../../../lib/jquery.transit.js',
                  '../../../lib/raphael.js',
                  '../../../lib/JSAV-min.js',
                  '_static/config.js',
                  '../../../lib/timeme-min.js',
                  '../../../lib/odsaUtils-min.js',
                  '../../../lib/odsaMOD-min.js',
                  'https://cdnjs.cloudflare.com/ajax/libs/d3/4.13.0/d3.min.js',
                  'https://d3js.org/d3-selection-multi.v1.min.js',
                  '../../../lib/dataStructures.js',
                  '../../../lib/conceptMap.js'
                ],
                "css_files": [
                  '../../../lib/normalize.css',
                  '../../../lib/JSAV.css',
                  '../../../lib/odsaMOD-min.css',
                  '../../../lib/jquery.ui.min.css',
                  #'https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css',
                  '../../../lib/odsaStyle-min.css',
                  '../../../lib/accessibility.css'                   
                ],
                "odsa_root_path": "../../../",
                "register":u"Register","login":u"Login","logout":u"Logout","about":u"About","close":u"Close Window","tofc":u"Table Of Contents","the_book":u"the book","username":u"Username","password":u"Password","password_confirm":u"Confirm Password","email":u"Email","username_email":u"Username or email","sign_in":u"Sign in","password_forget":u"Forgot your password?","about_msg":u"Created as part of the OpenDSA hypertextbook project. For more information, see. JSAV library version","contact":u"Contact Us","privacy":u"Privacy","license":u"License","report_bug":u"Report a bug","summary":u"Summary","os":u"Operating system","browser":u"Browser","description":u"Description","screenshot":u"Attach a screenshot (optional)","submit":u"Submit","login_warn":u"You must be logged in to receive credit","login_error":u"Incorrect username / password combination","bug_desc":u"***BUG** Please enter: (1) a consice description of the problem. (2) steps to reproduce bug. (3) the version of your browser and operating system.",}

if on_slides:
   html_context["css_files"].append('../../../lib/ODSAcoursenotes.css');
   html_context["odsa_scripts"].append('../../../lib/ODSAcoursenotes.js');

if 'haiku' != 'haiku':
  html_context['script_files'] += html_context['odsa_scripts']

# If not '', a 'Last updated on:' timestamp is inserted at every page bottom,
# using the given strftime format.
html_last_updated_fmt = '%b %d, %Y'

# Custom sidebar templates, maps document names to template names.
#html_sidebars = []

# Additional templates that should be rendered to pages, maps page names to
# template names.
#html_additional_pages = {}

# If false, no module index is generated.
#html_domain_indices = True

# If false, no index is generated.
#html_use_index = True

# If true, the index is split into individual pages for each letter.
#html_split_index = False

# If true, links to the reST sources are added to the pages.
#html_show_sourcelink = True

# If true, "Created using Sphinx" is shown in the HTML footer. Default is True.
#html_show_sphinx = True

# If true, "(C) Copyright ..." is shown in the HTML footer. Default is True.
#html_show_copyright = True

# If true, an OpenSearch description file will be output, and all pages will
# contain a <link> tag referring to it.  The value of this option must be the
# base URL from which the finished HTML is served.
#html_use_opensearch = ''

# This is the file name suffix for HTML files (e.g. ".xhtml").
#html_file_suffix = None

# Output file base name for HTML help builder.
htmlhelp_basename = 'OpenDSAdoc'


# -- My stuff ------------------------------------------------

todo_include_todos = True

#---- OpenDSA variables ---------------------------------------

# @efouh: despise the fact that we are using an url hash, gradebook still needs book name
book_name = 'ChalmersGU-DSABook'

# Boolean to control whether book will compile in local mode, which means no communication with the server
local_mode = True


#Absolute path to OpenDSA directory
odsa_path = '/home/noname/data-structures/OpenDSA/'

#Absolute path of eTextbook (build) directory
ebook_path = '/home/noname/data-structures/OpenDSA/Books/ChalmersGU-DSABook/html/'

#path (from the RST home) to the sourcecode directory that I want to use
sourcecode_path = '/home/noname/data-structures/OpenDSA/SourceCode/'

# Dictionary containing code_lang to extension mapping
code_lang = '{"Pseudo": {"ext": ["txt"], "label": "Pseudo-code", "lang": "java"}, "Java_Generic": {"ext": ["java"], "label": "Java", "lang": "java"}, "Python": {"ext": ["py"], "label": "Python", "lang": "python"}}'

# Boolean that controls whether or not code is displayed in tabs if more than one language is available
tabbed_codeinc = True

# Path to AV/ directory (local or remote)
av_dir = '/home/noname/data-structures/OpenDSA/'

# Path to Exercises/ directory (local or remote)
exercises_dir = '/home/noname/data-structures/OpenDSA/'

# Path to translation json file
translation_file = '/home/noname/data-structures/OpenDSA/tools/language_msg.json'
